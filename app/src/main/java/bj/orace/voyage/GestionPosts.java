package bj.orace.voyage;

public class GestionPosts {
        public int id;
        public String nom;
        public String description;
        public String nbrPlace;
        public String localisation;
        public String nomMet;

        public GestionPosts(int id,String nom, String description, String nbrPlace, String localisation, String nomMet){
            this.id = id;
            this.nom = nom;
            this.description = description;
            this.nbrPlace = nbrPlace;
            this.localisation = localisation;
            this.nomMet = nomMet;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(String nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getNomMet() {
        return nomMet;
    }

    public void setNomMet(String nomMet) {
        this.nomMet = nomMet;
    }

    // TODO: 04/12/2022 Creation de la methode toString
    @Override
    public String toString() {
        return "GestionPosts{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", nbrPlace='" + nbrPlace + '\'' +
                ", localisation='" + localisation + '\'' +
                ", nomMet='" + nomMet + '\'' +
                '}';
    }
}
