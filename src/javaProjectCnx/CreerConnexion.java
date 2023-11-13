package javaProjectCnx;

import java.sql.*;

public class CreerConnexion {

    public Connection myCnx() {
        Connection cn = null;
        // Création de la chaîne de connexion
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/commerces", "root", "");
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

 /*       str = "insert into etudiant(nom,prenom,filiere) values('Sparrow','Jack','Piraterie')";
        ps = cnx.prepareStatement(str);
        ps.execute();*/
  /*      str = "CREATE DATABASE IF NOT EXISTS commerces";
        ps = cnx.prepareStatement(str);
        ps.execute();*/
        str ="CREATE TABLE IF NOT EXISTS agence("
            	+ "noAg INT AUTO_INCREMENT,"
            	+ "noMag VARCHAR(99),"
            	+ "PRIMARY KEY (noAg)"
            	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute();
        str ="CREATE TABLE IF NOT EXISTS facture("
            	+ "noFac INT AUTO_INCREMENT,"
            	+ "dateFac VARCHAR(99),"
            	+ "PRIMARY KEY (noFac)"
            	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute();
        str = "CREATE TABLE IF NOT EXISTS client("
        	+ "coCli INT AUTO_INCREMENT,"
        	+ "nomCli VARCHAR(99),"
        	+ "rueCli VARCHAR(99),"
        	+ "villeCli VARCHAR(99),"
        	+ "noAg int,"
        	+ "PRIMARY KEY (coCli),"
        	+ "FOREIGN KEY (noAg) REFERENCES agence(noAg)"
        	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute();
        str ="CREATE TABLE IF NOT EXISTS commande("
            	+ "noBc INT AUTO_INCREMENT,"
            	+ "dateBc VARCHAR(99),"
            	+ "coCli INT,"
            	+ "PRIMARY KEY (noBc),"
            	+ "FOREIGN KEY (coCli) REFERENCES client(coCli)"
            	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute();
        str ="CREATE TABLE IF NOT EXISTS livraison("
            	+ "noBl INT AUTO_INCREMENT,"
            	+ "dateBl VARCHAR(99),"
            	+ "noBc INT,"
            	+ "noFac INT,"
            	+ "PRIMARY KEY (noBl),"
            	+ "FOREIGN KEY (noBc) REFERENCES commande(noBc),"
            	+ "FOREIGN KEY (noFac) REFERENCES fature(noFac)"
            	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute();
        str ="CREATE TABLE IF NOT EXISTS produit("
            	+ "refProd INT AUTO_INCREMENT,"
            	+ "desiProd VARCHAR(99),"
            	+ "codeTva INT,"
            	+ "PRIMARY KEY (refProd)"
            	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute();
        str ="CREATE TABLE IF NOT EXISTS tva("
            	+ "codeTva INT AUTO_INCREMENT,"
            	+ "tauxTva INT,"
            	+ "PRIMARY KEY (codeTva)"
            	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute();
        str ="CREATE TABLE IF NOT EXISTS composer("
            	+ "noBl INT,"
            	+ "refProd INT,"
            	+ "qteliv VARCHAR(99),"
            	+ "PRIMARY KEY (noBl, refProd),"
            	+ "FOREIGN KEY (noBl) REFERENCES livraison(noBl),"
            	+ "FOREIGN KEY (refProd) REFERENCES produit(refProd)"
            	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute();
        str ="CREATE TABLE IF NOT EXISTS concerner("
            	+ "noBc INT,"
            	+ "refProd INT,"
            	+ "qtecom VARCHAR(99),"
            	+ "pUnit INT,"
            	+ "PRIMARY KEY (noBc, refProd),"
            	+ "FOREIGN KEY (noBc) REFERENCES commande(noBc),"
            	+ "FOREIGN KEY (refProd) REFERENCES produit(refProd)"
            	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute(); //nomfrs, adrfrs, telfrs)
        str ="CREATE TABLE IF NOT EXISTS fournisseur("
            	+ "noFrs INT AUTO_INCREMENT,"
            	+ "nomFrs VARCHAR(99),"
            	+ "adrFrs VARCHAR(99),"
            	+ "telFrs VARCHAR(99),"
            	+ "PRIMARY KEY (noFrs)"
            	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute();
        str ="CREATE TABLE IF NOT EXISTS fournisseur("
            	+ "noFrs INT AUTO_INCREMENT,"
            	+ "nomFrs VARCHAR(99),"
            	+ "adrFrs VARCHAR(99),"
            	+ "telFrs VARCHAR(99),"
            	+ "PRIMARY KEY (noFrs)"
            	+ ")";
        ps = cnx.prepareStatement(str);
        ps.execute();
        /*
         * 
         * 
         * 
         * INSERT
         * 
         * 
         * 
         * 
         *//*
        str = "INSERT INTO agence (noMag) VALUES\n"
				+ "('sud-est'),\n"
				+ "('sud-ouest'),\n"
				+ "('sud'),\n"
				+ "('nord'),\n"
				+ "('nord-est'),\n"
				+ "('nord-ouest'),\n"
				+ "('ANTARIA'),\n"
				+ "('ABYSSE');";
		ps = cnx.prepareStatement(str);
		ps.execute();
		// table client
		str = "INSERT INTO client (nomCli, rueCli, villeCli, noAg) VALUES\n"
				+ "('Atef', 'general de gaule', 'Lille', 8),\n"
				+ "('Chloe', 'des braves', 'Cergy', 7);";
		ps = cnx.prepareStatement(str);
		ps.execute();
		// table tva
		str = "INSERT INTO tva (tauxTva) VALUES\n"
				+ "(20),\n"
				+ "(10);";
		ps = cnx.prepareStatement(str);
		ps.execute();
		// table produit
		str = "INSERT INTO produit (desiProd, codeTva) VALUES\n"
				+ "('Ecran', 1),\n"
				+ "('Souris', 1),\n"
				+ "('Tapis', 2),\n"
				+ "('Tableau', 2),\n"
				+ "('Roue', 1),\n"
				+ "('Unite', 1),\n"
				+ "('Chevre', 2),\n"
				+ "('Carabine', 1),\n"
				+ "('Chocolat', 2),\n"
				+ "('Feu de camp', 2);";
		ps = cnx.prepareStatement(str);
		ps.execute();
		// table fournisseur
		str = "INSERT INTO fournisseur (nomFrs, adrFrs, telFrs) VALUES\n"
				+ "('OREXAD', 'Bondue', '123456789'),\n"
				+ "('INSY2S', 'Lille', '123456789');";
		ps = cnx.prepareStatement(str);
		ps.execute();
		// table facture
		str = "INSERT INTO facture (dateFac) VALUES\n"
				+ "('2023-05-01'),\n"
				+ "('2023-04-11'),\n"
				+ "('2022-12-04'),\n"
				+ "('2022-12-13');";
		ps = cnx.prepareStatement(str);
		ps.execute();
		// table commande
		str = "INSERT INTO commande (dateBc, coCli, noFrs) VALUES\n"
				+ "('2023-05-23', 1, 2),\n"
				+ "('2023-04-29', 2, 1);";
		ps = cnx.prepareStatement(str);
		ps.execute();
		// table livraison
		str = "INSERT INTO livraison (dateBl, noBc, noFac) VALUES\n"
				+ "('2023-05-23', 1, 1),\n"
				+ "('2023-05-15', 2, 2);";
		ps = cnx.prepareStatement(str);
		ps.execute();
		// table composer
		str = "INSERT INTO composer (noBl, refProd, qteliv) VALUES\n"
				+ "(1, 1, 5),\n"
				+ "(1, 2, 7),\n"
				+ "(1, 3, 5),\n"
				+ "(1, 9, 23),\n"
				+ "(2, 7, 3),\n"
				+ "(2, 8, 2);";
		ps = cnx.prepareStatement(str);
		ps.execute();
		// table concerner
		str = "INSERT INTO concerner (noBc, refProd, qtecom, punit) VALUES\n"
				+ "(1, 1, 5, 200),\n"
				+ "(1, 2, 7, 9),\n"
				+ "(1, 3, 5, 4),\n"
				+ "(1, 9, 23, 2),\n"
				+ "(2, 7, 3, 120),\n"
				+ "(2, 8, 3, 999);";
		ps = cnx.prepareStatement(str);
		ps.execute();*/
        /*
         * 
         * 
         * REQUETES EXO AVC RESULTAT
         * 
         * 
         */
        // REQUETE N1 : 
		str = "SELECT p.refProd, p.desiProd "
				+ "FROM produit p "
				+ "WHERE p.refProd < 15 "
				+ "OR p.refProd BETWEEN 30 AND 40 "
				+ "OR p.refProd >= 70 "
				+ "ORDER BY p.desiProd ASC ";
		st = cnx.createStatement();
        rs = st.executeQuery(str);
		
        while (rs.next()) {
            System.out.println("Numéro : " + rs.getInt("refProd") + 
            		", Le nom : " + rs.getString("desiProd"));
        }
        // Requêtes avec retour de résultat : Le résultat TOUJOURS est un TABLEAU

        // Requêtes d'interrogation
/*
        str = "select * from etudiant where nom like 'Sparrow'";st = cnx.createStatement();
        rs = st.executeQuery(str);
*/
        // Parcourir le résultat stocké dans rs
/*
        while (rs.next()) {
            System.out.println("Numéro : " + rs.getInt("numero") + ", Le nom : " + rs.getString("nom"));
        }
*/
    }

}

