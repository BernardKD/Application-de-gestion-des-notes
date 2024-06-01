#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdint.h>
#include<unistd.h>
#include<time.h>
#include"projet.h"

void affichertaches(struct tache *t, int totaltaches){
	if(totaltaches==0)
		printf("\nAucune tache n'est enregistree !\n\n");
	else{
		printf("Liste des taches enregistres :\n\n");
		for(int i=0; i<totaltaches; i++){
			printf("Tache N* %d\n",i+1);
			printf("Responsable : %s\n",t[i].responsable);
			printf("Libelle : %s\n",t[i].libelle);
			printf("Budget : %f\n",t[i].budget);
			printf("statut : %s\n",t[i].statut);
			printf("Date de fin : %s\n",t[i].datefin);
			printf("\n");
		}
		sleep(totaltaches);
	}
	sleep(1);
	return;
}
void affichernombretaches(int totaltaches){
	printf("Le total de taches enregistrees est : %d\n",totaltaches);
	sleep(2);
}

void ajoutertaches(struct tache **t, int *totaltaches){
	int num;
	printf("Entrez le nombre de taches a ajouter : "); 
	scanf("%d",&num);
	*t = realloc(*t, (*totaltaches + num)*sizeof(struct tache));	

	if(*t==NULL){
		printf("Erreur lors de l'allocation\n");
		return;
	}
	for(int i=0; i<num; i++){
		(*t)[*totaltaches+i].responsable = malloc (30*sizeof(char));
		if((*t)[*totaltaches+i].responsable==NULL){
			printf("Erreur lors de l'allocation 1\n");
			return;
		}
		(*t)[*totaltaches+i].libelle = malloc (30*sizeof(char));
		if((*t)[*totaltaches+i].libelle==NULL){
			printf("Erreur lors de l'allocation 2\n");
			return;
		}
		(*t)[*totaltaches+i].statut = malloc (30*sizeof(char));	
		if((*t)[*totaltaches+i].statut==NULL){
			printf("Erreur lors de l'allocation 3\n");
			return;
		}
		(*t)[*totaltaches+i].datefin = malloc (30*sizeof(char));
		if((*t)[*totaltaches+i].datefin==NULL){
			printf("Erreur lors de l'allocation 4\n");
			return;
		}
		/*
		if((*t)[*totaltaches+i].responsable==NULL||(*t)[*totaltaches+i].libelle==NULL||(*t)[*totaltaches+i].statut==NULL||(*t)[*totaltaches+i].datefin==NULL){
			printf("Erreur lors de l'allocation\n");
			return;
		}
		*/
		(*t)[*totaltaches+i].numero=*totaltaches+1;
		printf("Entrez le responsable de la tache %d : ",i+1);
		scanf("%s",(*t)[*totaltaches+i].responsable);
		printf("Entrez le libelle de la tache %d : ",i+1);
		scanf("%s",(*t)[*totaltaches+i].libelle);
		printf("Entrez le budget de la tache %d : ",i+1);
		scanf("%f",&(*t)[*totaltaches+i].budget);
		printf("Entrez le statut de la tache %d : ",i+1);
		scanf("%s",(*t)[*totaltaches+i].statut);
		printf("Entrez la date de fin de la tache %d : ",i+1);
		scanf("%s",(*t)[*totaltaches+i].datefin);
	}
	*totaltaches += num;
	printf("Les taches ont ete enregistrees avec succes\n");
	sleep(2);
	return;
}

void modifiertaches(struct tache **t, int totaltaches){
	int nu;
	printf("Entrez le numero de la tache a modifier : "); 
	scanf("%d",&nu);
	if((nu==0)|(totaltaches==0)|(totaltaches<nu)|(nu<0))
		printf("Impossible ! La tache n'exite pas\n");
	else{
		printf("Entrez le responsable de la tache %d : ",nu);
		scanf("%s",(*t)[nu-1].responsable);
		printf("Entrez le libelle de la tache %d : ",nu);
		scanf("%s",(*t)[nu-1].libelle);
		printf("Entrez le budget de la tache %d : ",nu);
		scanf("%f",&(*t)[nu-1].budget);
		printf("Entrez le statut de la tache %d : ",nu);
		scanf("%s",(*t)[nu-1].statut);
		printf("Entrez la date de fin de la tache %d : ",nu);
		scanf("%s",(*t)[nu-1].datefin);
		printf("La tache a ete modifiee avec succes\n");
		sleep(1);
	}
	sleep(1);
	return;
}
