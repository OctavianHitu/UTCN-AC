#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <sys/mman.h>
#include <stdlib.h>

#define fifo_name_req "REQ_PIPE_71549"
#define fifo_name_res "RESP_PIPE_71549"
char x[]= "CONNECT\0";
int sizex=7;
char request[1000]={};
int reqsize;
char request2[1000]={};
int reqsize2;
char request3[1000]={};
int reqsize3;
char pong[]="PONG\0";
int nrtema = 71549;
int successize=7;
int errorsize=5;
unsigned int shm;

int main(int argc, char** argv)
{
    int fds;
    int fdq;
    int validator=1;


    if(access(fifo_name_res, 0) == 0) 
    {
        unlink(fifo_name_res);
    }   

    if(mkfifo(fifo_name_res,0600)!=0)
    {
        printf("ERROR\ncannot create the response pipe | cannot open the request pipe\n");
        validator=0;
        return 1;

    }
    
    fdq=open(fifo_name_req,O_RDONLY);
    fds=open(fifo_name_res,O_WRONLY);

    if(fdq ==-1)
    {
        validator=0;
        printf("ERROR\n cannot create the response pipe | cannot open the request pipe");
        return 1;
    }
    if(fds ==-1)
    {
        validator=0;
        printf("ERROR\n cannot create the response pipe | cannot open the request pipe");
        return 1;
    }

    write(fds,&sizex,1);
    write(fds,&x,sizex);
    if(validator==1)
    {
        printf("SUCCESS\n");
    }

    for(;;)
    {
    read(fdq,&reqsize,1);
    read(fdq,&request,reqsize);
    if(strcmp(request,"PING")==0)
    {
        write(fds,&reqsize,1);
        write(fds,&request,reqsize);
        write(fds,&reqsize,1);
        write(fds,&pong,reqsize);
        write(fds,&nrtema,sizeof(nrtema));

    }
    if(strcmp(request,"CREATE_SHM")==0)
    {
        read(fdq,&reqsize2,1);
        read(fdq,&request2,reqsize2);
        

       
        char * sharedchar=NULL;
        shm=shm_open("/RtvUzEeX",O_CREAT | O_RDWR,0664);
        if(shm<1)
        {
            write(fds,&reqsize,1);
            write(fds,&request,reqsize);
            write(fds,&errorsize,1);
            write(fds,"ERROR",errorsize);
            return 1;

        }
        ftruncate(shm,2172549);
        sharedchar= ( char *)mmap(0,sizeof(char),PROT_READ | PROT_WRITE,MAP_SHARED,shm,0); ;
        if(sharedchar== (void*)-1)
        {
            write(fds,&reqsize,1);
            write(fds,&request,reqsize);
            write(fds,&errorsize,1);
            write(fds,"ERROR",errorsize);
            return 1;
        }
        write(fds,&reqsize,1);
        write(fds,&request,reqsize);
        write(fds,&successize,1);
        write(fds,"SUCCESS",successize);

        munmap(sharedchar,sizeof(char));
        sharedchar=NULL;
 
    }

    if(strcmp(request,"WRITE_TO_SHM")==0)
    {

        unsigned int reqoff=0;
        unsigned int reqval=0;
       
        read(fdq,&reqoff,sizeof(unsigned int));
        read(fdq,&reqval,sizeof(unsigned int));
        if(reqoff<0 || reqoff > 2172549)
        {
            write(fds,&reqsize,1);
            write(fds,&request,reqsize);
            write(fds,&errorsize,1);
            write(fds,"ERROR",errorsize);
            return 1;
        }
        else{
        lseek(shm,reqoff,SEEK_SET);
        write(shm,&reqval,sizeof(reqval));

        write(fds,&reqsize,1);
        write(fds,&request,reqsize);
        write(fds,&successize,1);
        write(fds,"SUCCESS",successize);
        }

    }

    if(strcmp(request,"MAP_FILE")==0)
    {
        read(fdq,&reqsize2,1);
        read(fdq,&request2,reqsize2);
        
        char * sharedchar=NULL;
        shm=shm_open("/RtvUzEeX", O_RDWR,0664);
        sharedchar= ( char *)mmap(0,sizeof(char),PROT_READ | PROT_WRITE,MAP_SHARED,shm,0); ;
        if(sharedchar==(void*)-1)
        {
            write(fds,&reqsize,1);
            write(fds,&request,reqsize);
            write(fds,&errorsize,1);
            write(fds,"ERROR",errorsize);
            return 1;
        }
  
        
        write(fds,&reqsize,1);
        write(fds,&request,reqsize);
        write(fds,&successize,1);
        write(fds,"SUCCESS",successize);

        munmap(sharedchar,sizeof(char));
        sharedchar=NULL;
        
        return 0;

    }
    
    if(strcmp(request,"READ_FROM_FILE_OFFSET")==0)
    {
        return 0;
    }

    if(strcmp(request,"READ_FROM_FILE_SECTION")==0)
    {
        return 0;
    }

    if(strcmp(request,"READ_FROM_LOGICAL_SPACE_OFFSET")==0)
    {
        return 0;
    }


    if(strstr(request,"EXIT"))
    {
    close(fds);
    close(fdq);
    unlink(fifo_name_res);
    return 0;  
    }
    }

    return 0;
}




