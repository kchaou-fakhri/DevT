package Model;

public class job {


    private String titre;
    private String desc;
    private String ville;
    private String domaine;
    private String mail;
    private String budget;
    private  String userId;

    public job(String desc, String domaine, String mail, String titre, String ville, String userId)
    {
        this.desc = desc;
        this.domaine = domaine;
        this.mail=mail;
        this.titre = titre;
        this.ville = ville;
        this.userId = userId;

    }
    public job()
    {

    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public String toString() {
        return  titre ;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
