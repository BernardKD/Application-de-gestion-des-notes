#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<stdint.h>
#include<unistd.h>
#include<time.h>
#include"projet.h"
//static int num=0;
int main()
{
	int n, num=0;
	struct tache *Tache =NULL;
	system("clear");
	do{
		system("clear");
		printf("###############GESTION DES TACHES################\n");
		printf("1- Afficher les taches\n2- Enregistrer des taches\n3- Mettre a jour une tache\n4- Afficher le total des taches\n0- Quitter\n");
		printf("Entrez le chiffre coresspondant a votre a votre choix : ");
		scanf("%d",&n);	
		switch (n)
		{
			case 1 :
				system("clear");
				affichertaches(Tache,num);
				break;

			case 2 :
				system("clear");
				ajoutertaches(&Tache,&num);
				break;

			case 3 :
				system("clear");
				modifiertaches(&Tache,num);
				break;

			case 4 :
				system("clear");
				affichernombretaches(num);
				break;

			case 0 :
				system("clear");
				break;

			default:
				printf("\nEreur ! Veuillez choisir une option valable.\n");
				sleep(1);
			break;
		}
		printf("\n");
	}while(n!=0);
	for(int i = 0; i<num; i++){
		free(Tache[i].responsable);	
	}
	free(Tache);
	printf("Au revoir!\n");
	sleep(1);
	system("clear");
	return 0;
}

