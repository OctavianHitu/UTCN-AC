#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>

int var1=1;

typedef struct
    {
        char sect_name[12];
        int sect_type;
        int sect_offset;
        int sect_size;
    }sect;


int numeend(char *nume,char * sir)
{
    int i,j;
    for(i=strlen(sir),j=strlen(nume);i>=0 && j >= 0;i--,j--)
    {
        if(nume[j]!=sir[i])
        {
            return 0;
        }
    }
    if(j == -1 && i >= 0)
    {
        return 0;
    }
    return 1;
}


int listare(const char *path, int recursiv, char *sir_cautat, int size)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char filePath[512];
    struct stat statbuf;

    dir=opendir(path);
    if(dir ==NULL)
    {
        perror("Could not open directory");
        return -1;
    }

   while((entry = readdir(dir)) != NULL)
   {   
       if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0)
       {
           snprintf(filePath, 512, "%s/%s", path, entry->d_name);
           if(lstat(filePath, &statbuf) == 0) 
           {   
               if(strlen(sir_cautat) == 0 || numeend(entry->d_name,sir_cautat) == 1)
               {
                   if(S_ISDIR(statbuf.st_mode))
                    {
                       if(size==-1)
                        {
                            printf("%s\n", filePath);
                        }
                       if(recursiv)
                       {
                            listare(filePath, recursiv, sir_cautat, size);
                       }
                    }
                   else if(size == -1 || statbuf.st_size > size) {
                       printf("%s\n", filePath);
                   }

               }
                
           }
       }

   }
    closedir(dir);
    return 0;
}

int citirenumar(int fd,int nrbiti)
{
    unsigned char sursa[4];
    read(fd,sursa,nrbiti);
        
        u_int rez = sursa[nrbiti - 1];
        for(int i = nrbiti - 2; i >= 0; --i)
                rez = (rez << 8) | sursa[i];
        return rez;
}


int lparse(const char *path)
{
    char magic[3]={0,0,0};
    int header_size, version,no_of_sections;
    

   
    int fd=-1;
    fd= open(path,O_RDONLY);
    if(fd==-1)
    {
        perror("Could not open");
        return -1;
    }
    else
    {
    lseek(fd,-4,SEEK_END);
    header_size=citirenumar(fd,2);
    read(fd,magic,2);  
    lseek(fd,-header_size,SEEK_END);
    version=citirenumar(fd,2);
    no_of_sections=citirenumar(fd,1);
    sect section[no_of_sections];
    off_t cursor;
    cursor=lseek(fd,0,SEEK_CUR);
    for(int i=0;i<no_of_sections;i++)
    {
        read(fd,section[i].sect_name,11);

       section[i].sect_type=citirenumar(fd,4);
        section[i].sect_offset=citirenumar(fd,4);
        section[i].sect_size=citirenumar(fd,4);
  
        if(section[i].sect_type!=49 && section[i].sect_type!=85)
        {
            printf("ERROR\nwrong sect_types\n");
            return -1;
        }
    }

    if(strcmp(magic,"AH")!=0)
    {
        printf("ERROR\nwrong magic\n");
        return -1;
    }
    else if(version<58 || version >99)
    {
        printf("ERROR\nwrong version\n");
        return -1;
    }
    else if(no_of_sections<7 || no_of_sections>13)
    {
        printf("ERROR\nwrong sect_nr\n");
        return -1;
    }
    
    
    printf("SUCCESS\nversion=%d\nnr_sections=%d\n",version,no_of_sections);
    lseek(fd,cursor,SEEK_SET);
    for(int i=0;i<no_of_sections;i++)
    {
        read(fd,section[i].sect_name,11);
        section[i].sect_name[11]='\0';
        section[i].sect_type=citirenumar(fd,4);
        section[i].sect_offset=citirenumar(fd,4);
        section[i].sect_size=citirenumar(fd,4);
        printf("section%d: %s %d %d\n",i+1,section[i].sect_name,section[i].sect_type,section[i].sect_size);

    }
    

    }
return 0;
}

int nrdelinii(int fd,int size,int offset)
{
    int nrlinii=1;
    char *buffer=NULL;
    int poz=0;
    buffer=(char*)malloc(size * sizeof(char));
    lseek(fd,offset,SEEK_SET);
    read(fd,buffer,size);

    for(int i=poz;i<size;i++)
    {
        if(buffer[i]=='\n')
        {
            nrlinii++;
        }
    }
    nrlinii++;
    free(buffer);
    return nrlinii;
    
}


int linieext(int fd,int size,int numarline,char *line,int offset)
{
    char *buffer=NULL;
    int poz=0;
    int iline=0;
    int ich=0;
    buffer=(char*)malloc(size * sizeof(char));
    lseek(fd,offset,SEEK_SET);
    read(fd,buffer,size);


    for(int i=poz;i<size;i++)
    {
        if(iline+1==numarline)
        {
            if(buffer[i]=='\n')
            {
                line[ich]=0;
               
                break;
            }
            line[ich]=buffer[i];
            ich++;
        }
        else
        {
            if(buffer[i]=='\n')
            {
                iline++;
            }
        }
        
    }
    free(buffer);
    return 0;
}

int extractf(const char *path,int sectionnum,int line)
{
    char magic[3]={0,0,0};
    int header_size, version,no_of_sections;
    

   
    int fd=-1;
    fd= open(path,O_RDONLY);
    if(fd==-1)
    {
        perror("Could not open");
        return -1;
    }
    else
    {
    lseek(fd,-4,SEEK_END);
    header_size=citirenumar(fd,2);
    read(fd,magic,2);  
    lseek(fd,-header_size,SEEK_END);
    version=citirenumar(fd,2);
    no_of_sections=citirenumar(fd,1);
    sect section[no_of_sections];

    if(strcmp(magic,"AH")!=0)
    {
        printf("ERROR\ninvalid file\n");
        return -1;
    }
    else if(version<58 || version >99)
    {
       printf("ERROR\ninvalid file\n");
        return -1;
    }
    else if(no_of_sections<7 || no_of_sections>13)
    {
        printf("ERROR\ninvalid file\n");
        return -1;
    }

    for(int i=0;i<no_of_sections;i++)
    {
        read(fd,section[i].sect_name,11);

       section[i].sect_type=citirenumar(fd,4);
        section[i].sect_offset=citirenumar(fd,4);
        section[i].sect_size=citirenumar(fd,4);
  
        if(section[i].sect_type!=49 && section[i].sect_type!=85)
        {
            printf("ERROR\ninvalid file\n");
            return -1;
        }
        if(i==sectionnum-1)
        {
            char *linie=(char *)malloc(section[i].sect_size * sizeof(char));
            int numlinii=nrdelinii(fd,section[i].sect_size,section[i].sect_offset);
            line= numlinii-line;
            if(linieext(fd,section[i].sect_size,line,linie,section[i].sect_offset)!=0)
            {
                printf("ERROR\ninvalid line\n");
                free(linie);
                return -1;
            }
            else
            {
                
                printf("SUCCESS\n");
                printf("%s\n",linie);
                free(linie);
                return 0;
            }


        }
    }

    }

    printf("ERROR\ninvalid section\n");
    return 0;
}

int verificarefindall(const char *path)
{
    char magic[3]={0,0,0};
    int header_size, version,no_of_sections;
    int nrsect=0;

    int fd=-1;
    fd= open(path,O_RDONLY);
    if(fd==-1)
    {
        perror("Could not open");
        return -1;
    }
    else
    {
        lseek(fd,-4,SEEK_END);
        header_size=citirenumar(fd,2);
        read(fd,magic,2);  
        lseek(fd,-header_size,SEEK_END);
        version=citirenumar(fd,2);
        no_of_sections=citirenumar(fd,1);
        sect section[no_of_sections];

        if(strcmp(magic,"AH")!=0)
        {
            return -1;
        }
        else if(version<58 || version >99)
        {
            return -1;
        }
        else if(no_of_sections<7 || no_of_sections>13)
        {
            return -1;
        }


        for(int i=0;i<no_of_sections;i++)
        {
            read(fd,section[i].sect_name,11);

            section[i].sect_type=citirenumar(fd,4);
            section[i].sect_offset=citirenumar(fd,4);
            section[i].sect_size=citirenumar(fd,4);
    
            if(section[i].sect_type!=49 && section[i].sect_type!=85)
            {
                return -1;
            }
            if(section[i].sect_size<= 1279)
            {
                nrsect++;
            }

        }
    }
    if(nrsect==no_of_sections)
            {
                return 0;
            }

    return -1;

}

int findallf(const char *path)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char filePath[512];
    struct stat statbuf;


    dir=opendir(path);
    if(dir ==NULL)
    {
        perror("invalid directory path");
        return -1;
    }
    else
    {
        if(var1==1)
        {
            printf("SUCCESS\n");
            var1--;
        }
    }

   while((entry = readdir(dir)) != NULL)
   {   
       if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0)
       {
           snprintf(filePath, 512, "%s/%s", path, entry->d_name);
           if(lstat(filePath, &statbuf) == 0) 
           {
                
                if(S_ISDIR(statbuf.st_mode))
                {
                    findallf(filePath);
                   
                }
                else if(S_ISREG(statbuf.st_mode))
                {
                    if(verificarefindall(filePath)==0)
                    {
                        printf("%s\n", filePath);
                    }
                }
           }
       }
   
   }
    closedir(dir);
    return 0;

}

int main(int argc, char **argv)
{
    char newpath[512]="";
    struct stat statbuf;
    char numar[20];
    char line2[20];
    int nr=-1;
    int section;
    char section2[30];
    char sir[30];
    int list = 0;
    int parse = 0;
    int extract = 0;
    int recursive = 0;
    int line;
    int findall=0;

    if(argc >= 2)
    {
    
        for(int j=0;j<argc;j++)
        {
            if(strncmp(argv[j],"path=",5)==0)
            {
                strcpy(newpath , strtok(argv[j],"="));
                strcpy(newpath, strtok(NULL," "));
            }
            else if(strncmp(argv[j],"size_greater=",11)==0)
            {
                strcpy(numar , strtok(argv[j],"="));
                strcpy(numar, strtok(NULL," "));
                nr=atoi(numar);              
            }
            else if(strncmp(argv[j],"name_ends_with=",14)==0)
            {
                strcpy(sir , strtok(argv[j],"="));
                strcpy(sir, strtok(NULL," "));
                            
            }
            else if(strcmp(argv[j], "variant") == 0)
            {
                printf("71549\n");
                return 0;
            }
            else if(strcmp(argv[j],"list")==0) 
            {
                list = 1;
            }
            else if(strcmp(argv[j],"recursive")==0) 
            {
                recursive = 1;
            }
            else if(strcmp(argv[j],"parse")==0)
            {
                parse=1;
            }
            else if(strcmp(argv[j],"extract")==0)
            {
                extract=1;
            }
            else if(strncmp(argv[j],"section=",8)==0)
            {
                strcpy(section2, strtok(argv[j],"="));
                strcpy(section2, strtok(NULL," "));
                section=atoi(section2);
            }
            else if(strncmp(argv[j],"line=",5)==0)
            {
                strcpy(line2 , strtok(argv[j],"="));
                strcpy(line2, strtok(NULL," "));
                line=atoi(line2); 

            }
            else if(strcmp(argv[j],"findall")==0)
            {
                findall=1;
            }

        }

        if(list) 
        {
            lstat(newpath, &statbuf);
            if(S_ISDIR(statbuf.st_mode))
            {
                printf("SUCCESS\n");
                listare(newpath, recursive, sir, nr);
            }
        }
        else if(parse)
        {
            
            lparse(newpath);
        }
        else if(extract)
        {
            extractf(newpath,section,line);
        }
        else if(findall)
        {
            findallf(newpath);
        }
    }


    return 0;
}