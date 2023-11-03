package javaProjectCnx;

import java.sql.*;

public class CreerConnexion {

    public Connection myCnx() {
        Connection cn = null;
        // Création de la chaîne de connexion
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblio", "root", "");
            System.out.println("Connexion réussie");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connexion pas réussie enfaite");
        }
        return cn;
    }

    public static void main(String[] args) throws SQLException {
        // Récupérer l'objet Connection
        CreerConnexion db = new CreerConnexion();
        Connection cnx = null;
        cnx = db.myCnx();
        // Lancer requêtes

        // PreparedStatement (sans retour )
        // Déclaration : 
        String str = "";
        PreparedStatement ps; // pour les requêtes de mise à jour
        Statement st; // pour les requêtes d'interrogations
        ResultSet rs; // jeu de résultat

        str = "insert into etudiant(nom,prenom,filiere) values('Sparrow','Jack','Piraterie')";
        ps = cnx.prepareStatement(str);
        ps.execute();

        // Requêtes avec retour de résultat : Le résultat TOUJOURS est un TABLEAU

        // Requêtes d'interrogation

        str = "select * from etudiant where nom like 'Sparrow'";st = cnx.createStatement();
        rs = st.executeQuery(str);

        // Parcourir le résultat stocké dans rs

        while (rs.next()) {
            System.out.println("Numéro : " + rs.getInt("numero") + ", Le nom : " + rs.getString("nom"));
        }

    }

}

