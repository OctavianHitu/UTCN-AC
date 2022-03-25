#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <threads.h>
#include <pthread.h>
#include <semaphore.h>
#include "a2_helper.h"


#define nrthreads  4

typedef struct {
    int id;
    pthread_mutex_t *lock1;
    pthread_mutex_t *lock2; 
} thread_p3;

typedef struct {
    int id;
    sem_t *sem;
} thread_p8;

typedef struct {
    int id;
}thread_p6;


void *tr_fct(void *arg)
{
    thread_p3 *d = (thread_p3*)arg;
    if(d->id == 2) {
        info(BEGIN, 3, 2);
        pthread_mutex_unlock(d->lock2);
        pthread_mutex_lock(d->lock1);
        info(END, 3, 2);
    }
    else if(d->id == 3) {
        pthread_mutex_lock(d->lock2);
        info(BEGIN, 3, 3);       
        info(END, 3, 3);
        pthread_mutex_unlock(d->lock1);
    }
    else {
        info(BEGIN, 3, d->id);       
        info(END, 3, d->id);
    }
    return NULL;
}


void *tr_fct2(void *arg)
{
    thread_p8 *d = (thread_p8*)arg;
    sem_wait(d->sem);
    info(BEGIN, 8, d->id);       
    info(END, 8, d->id);
    sem_post(d->sem);
    return NULL;
}

void *tr_fct3(void *arg)
{
    thread_p3 *d = (thread_p3*)arg;
    info(BEGIN, 6, d->id);       
    info(END, 6, d->id);
    return NULL;
}

int main(){



    init();
    info(BEGIN, 1, 0);


    pid_t p2,p9;

    p2=fork();
    if(p2==-1)
    {
        return -1;
    }
    else if(p2==0)
    {
        info(BEGIN,2,0);

        pid_t p3,p7;

        p3=fork();
        if(p3==-1)
        {
            return -1;
        }
        else if(p3==0)
        {
            info(BEGIN,3,0);
            //threads
            thread_p3 d[4];
            pthread_t tids[4];

            pthread_mutex_t lock1, lock2;
            pthread_mutex_init(&lock1, NULL);
            pthread_mutex_init(&lock2, NULL);

            pthread_mutex_lock(&lock1);
            pthread_mutex_lock(&lock2);
            for(int i=0;i<4;i++)
            {
                d[i].id = i + 1;
                d[i].lock1 = &lock1;
                d[i].lock2 = &lock2;
                pthread_create(&tids[i],NULL,tr_fct,&d[i]);
            }
            for(int i=0;i<4;i++)
            {
                pthread_join(tids[i],NULL);
            }
            pthread_mutex_destroy(&lock1);
            pthread_mutex_destroy(&lock2);
            //threads
 
            pid_t p4,p5;

            p4=fork();
            if(p4==-1)
            {
                return -1;
            }
            else if(p4==0)
            {
                info(BEGIN,4,0);
                info(END,4,0);
            }
            else
            {
                waitpid(p4,NULL,0);
                p5=fork();
                if(p5==-1)
                {
                    return -1;
                }
                else if(p5==0)
                {
                    info(BEGIN,5,0);

                    pid_t p6;
                    p6=fork();
                    if(p6==-1)
                    {
                        return -1;
                    }else if(p6==0)
                    {
                        info(BEGIN,6,0);
                        //threads
                        thread_p6 d[5];
                        pthread_t tids[5];

                        for(int i=0;i<5;i++)
                        {
                            d[i].id = i + 1;
                            pthread_create(&tids[i],NULL,tr_fct3,&d[i]);
                        }
                        for(int i=0;i<5;i++)
                        {
                            pthread_join(tids[i],NULL);
                        }
                        //threads
                        pid_t p8;
                        p8=fork();
                        if(p8==-1)
                        {
                            return -1;
                        }
                        else if(p8==0)
                        {
                            info(BEGIN,8,0);
                            //threads
                            thread_p8 d[36];
                            pthread_t tids[36];

                            sem_t sem;
                            sem_init(&sem, 0, 4);
                            for(int i=0;i<36;i++)
                            {
                                d[i].id = i + 1;
                                d[i].sem = &sem;
                                pthread_create(&tids[i],NULL,tr_fct2,&d[i]);
                            }
                            for(int i=0;i<36;i++)
                            {
                                pthread_join(tids[i],NULL);
                            }
                            sem_destroy(&sem);
                            //threads
                            info(END,8,0);

                        }
                        else
                        {
                            waitpid(p8,NULL,0);
                        info(END,6,0);
                        }
                    }
                    else
                    {
                    waitpid(p6,NULL,0);
                    info(END,5,0);
                    }
                }
                else
                {
                    waitpid(p5,NULL,0);
                    info(END,3,0);
                }
            }
    
        }
        else
        {
            waitpid(p3,NULL,0);
            p7=fork();
            if(p7==-1)
            {
                return -1;
            }
            else if(p7==0)
            {
                info(BEGIN,7,0);
                info(END,7,0);
            }
            else
            {
                waitpid(p7,NULL,0);
                info(END,2,0);
            }
        }
        
    }
    else
    {
        waitpid(p2,NULL,0);
        p9=fork();
        if(p9==-1)
        {
            return -1;
        }
        else if(p9==0)
        {
            info(BEGIN,9,0);
            info(END,9,0);
        }
        else
        {
            waitpid(p9,NULL,0);
            info(END,1,0);
        }
    }
    

    return 0;
}
