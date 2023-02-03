#include <stdio.h>
#include <malloc.h>
#include <float.h>

double a=1.;
double b=2.;
double c=3.;
double g1=2.;
double g2=1.;
double g3=2.;
double p1=4.;
double p2=5.;
double r2=-1.;
double s1=-2.;
double s2=-3.;
double mu2=4.;
double mu3=5.;

void ForwardEuler()
  {
  int i;
  double dt=0.01,dxdt,dydt,dzdt,t=0.,x=0.,y=0.,z=0.;
  printf("Solving ODE Using Forward Euler Method\n");
  printf("  t     x       y      z\n");
  printf("%4.02lf %6.02lf %7.02lf %6.02lf\n",t,x,y,z);
  for(i=0;i<100;i++)
    {
    dxdt=r2*y*(1.-b*y)-a*x*y/(g2+y);
    dydt=c*y-mu2*x+p1*x*z/(g1+z)+s1;
    dzdt=p2*x*y/(g3+y)-mu3*z+s2;
    x+=dxdt*dt;
    y+=dydt*dt;
    z+=dzdt*dt;
    t+=dt;
    printf("%4.02lf %6.02lf %7.02lf %6.02lf\n",t,x,y,z);
    }
  }

void RK4(void dYdX(double,double*,double*),double*X,double dX,double*Y,double*dY,int n)
  {
  int i,j;
  double A[3]={0.5,0.5,1.},*V,*W,Xi;
  double B[4]={1./6.,1./3.,1./3.,1./6.};
  V=calloc(  n,sizeof(double));
  W=calloc(4*n,sizeof(double));
  Xi=*X;
  dYdX(Xi,Y,W);
  for(i=1;i<4;i++)
    {
    Xi=*X+dX*A[i-1];
    for(j=0;j<n;j++)
      {
      dY[j]=A[i-1]*W[n*(i-1)+j];
      V[j]=Y[j]+dX*dY[j];
      }
    dYdX(Xi,V,W+n*i);
    }
  for(j=0;j<n;j++)
    {
    dY[j]=0.;
    for(i=0;i<4;i++)
      dY[j]+=B[i]*W[n*i+j];
    Y[j]+=dX*dY[j];
    }
  *X+=dX;
  free(V);
  free(W);
  }

void ODE(double t,double*q,double*dq)
  {
  dq[0]=r2*q[1]*(1.-b*q[1])-a*q[0]*q[1]/(g2+q[1]);
  dq[1]=c*q[1]-mu2*q[0]+p1*q[0]*q[2]/(g1+q[2])+s1;
  dq[2]=p2*q[0]*q[1]/(g3+q[1])-mu3*q[2]+s2;
  }

void RungeKutta4()
  {
  int i;
  double dq[3],dt=0.01,q[3]={0.,0.,0.},t=0.;
  printf("Solving ODE Using 4th Order Runge-Kutta Method\n");
  printf("  t     x       y      z\n");
  printf("%4.02lf %6.02lf %7.02lf %6.02lf\n",t,q[0],q[1],q[2]);
  for(i=0;i<100;i++)
    {
    RK4(ODE,&t,dt,q,dq,3);
    printf("%4.02lf %6.02lf %7.02lf %6.02lf\n",t,q[0],q[1],q[2]);
    }
  }

int main(int argc,char**argv,char**envp)
  {
  ForwardEuler();
  RungeKutta4();
  return(0);
  }
    