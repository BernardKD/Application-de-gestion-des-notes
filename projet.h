#ifndef _PROJET_
    #define _PROJET_
	struct tache{
		char *responsable;
		int numero;
		char *libelle;
		float budget;			
		char *statut;
		char *datefin;
	};
	void affichertaches(struct tache *t, int totaltaches);
	void affichernombretaches(int totaltaches);
	void ajoutertaches(struct tache **t, int *totaltaches);
	void modifiertaches(struct tache **t, int totaltaches);
#endif
