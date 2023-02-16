package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private int nbEtal;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		this.villageois = new Gaulois[nbVillageoisMaximum];
	}

	private static class Marche {
		private Etal[] etals;
		private int nbEtal;

		private Marche(int nbEtal) {
			super();
			this.nbEtal = nbEtal;
			this.etals = new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				this.etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			this.etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			int i = 0;
			while (this.etals[i].isEtalOccupe() && i < this.nbEtal) {
				i++;
			}
			if (i == this.nbEtal - 1) {
				return -1;
			} else {
				return i;
			}
		}

		private Etal[] trouverEtals(String produit) {
			int nbEtalSpe = 0;
			for (int i = 0; i < this.nbEtal; i++) {
				if (this.etals[i].isEtalOccupe() && this.etals[i].contientProduit(produit)) {
					nbEtalSpe++;
				}
			}
			Etal[] etalSpe = new Etal[nbEtalSpe];
			int indiceEtalSpe = 0;
			for (int i = 0; i < this.nbEtal; i++) {
				if (this.etals[i].isEtalOccupe() && this.etals[i].contientProduit(produit)) {
					etalSpe[indiceEtalSpe] = this.etals[i];
					indiceEtalSpe++;
				}
			}
			return etalSpe;
		}
	}

	public String getNom() {
		return this.nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (this.nbVillageois < this.villageois.length) {
			this.villageois[this.nbVillageois] = gaulois;
			this.nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(this.chef.getNom())) {
			return this.chef;
		}
		for (int i = 0; i < this.nbVillageois; i++) {
			Gaulois gaulois = this.villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (this.nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + this.chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + this.chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < this.nbVillageois; i++) {
				chaine.append("- " + this.villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}