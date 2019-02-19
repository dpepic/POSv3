package DB;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

public class Comm {

	public static String[] naziviKolona;
	public static Vector<String[]> sviRedovi = new Vector<String[]>();
	public static Vector<String> PK = new Vector<String>();

	private static Connection nasaKonekcija  = null;
	private static Statement kom = null;

	public static void ozbiljnaKonekcija()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			nasaKonekcija = DriverManager.getConnection("jdbc:mysql://localhost", "javaTest", "NekaSifra");
			kom = nasaKonekcija.createStatement();
			kom.executeQuery("USE pos");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		} 
	}

	public static void dajPodatke(String tip) 
	{
		ozbiljnaKonekcija();
		sviRedovi.clear();
		PK.clear();
		try
		{	
			ResultSet rs = kom.executeQuery(String.format("CALL dajTabelu('%s')", tip));	
			int brojKolona = dajNaziveKolona(rs);

			while (rs.next())
			{     
				String[] red = new String[brojKolona];
				PK.add(rs.getString(1));
				for (int i = 2; i <= brojKolona; i++)
				{
					red[i-2] = rs.getString(i);

				}
				sviRedovi.add(red);
			}		
			nasaKonekcija.close();

		} catch (SQLException joj)
		{
			joj.printStackTrace();
		}
	}

	public static int obrisiRed(String IDreda, String tip)
	{
		ozbiljnaKonekcija();
		try 
		{
			kom.executeQuery(String.format("CALL obrisiRed('%s', '%s')", IDreda, tip));
			nasaKonekcija.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public static void dajArtikleSaRacuna(String PK)
	{
		sviRedovi.clear();
		Comm.PK.clear();
		ozbiljnaKonekcija();

		try
		{
			ResultSet rs = kom.executeQuery(String.format("CALL dajArtikleSaRacuna('%s')", PK));
			int brojKolona = dajNaziveKolona(rs);

			while (rs.next())
			{
				String[] red = new String[brojKolona];
				Comm.PK.add(rs.getString(1));
				for (int i = 2; i <= brojKolona; i++)
				{
					red[i - 2] = rs.getString(i);
				}
				sviRedovi.add(red);
			}
			nasaKonekcija.close();
		} catch (SQLException joj)
		{
			joj.printStackTrace();
		}
	}
	public static void dajArtikal(String PK)
	{
		sviRedovi.clear();
		ozbiljnaKonekcija();

		try
		{
			ResultSet rs = kom.executeQuery(String.format("CALL dajArtikal('%s')", PK));
			if (rs.next())
			{
				int brojKolona = dajNaziveKolona(rs);
				String[] red = new String[brojKolona];
				for (int i = 0; i < brojKolona; i++)
				{
					red[i] = rs.getString(i+1);
				}
				sviRedovi.add(red);
			}
			nasaKonekcija.close();
		} catch (SQLException joj)
		{
			joj.printStackTrace();
		}
	}
	public static void dajFirmu(String PK)
	{
		sviRedovi.clear();
		ozbiljnaKonekcija();

		try 
		{
			ResultSet rs = kom.executeQuery(String.format("CALL dajFirmu('%s')", PK));
			int brojKolona = dajNaziveKolona(rs);  
			if (rs.next())
			{
				String[] red = new String[brojKolona]; 
				for (int i = 0; i < brojKolona; i++)
				{
					red[i] = rs.getString(i+1);
				}
				sviRedovi.add(red);
				rs = kom.executeQuery(String.format("CALL dajAdrese('%s')", PK));

				brojKolona = dajNaziveKolona(rs);
				while (rs.next())
				{
					red = new String[brojKolona];
					for (int i = 0; i < brojKolona; i++)
					{
						red[i] = rs.getString(i+1);
					}
					sviRedovi.add(red);
				}
			}
			nasaKonekcija.close();
		} catch (SQLException joj)
		{
			joj.printStackTrace();
		}
	}

	public static void izmenaAdrese(String PK, String pbr, String grad,
			String ulica, String broj, String PKlica)
	{
		ozbiljnaKonekcija();
		try
		{
			kom.executeQuery(String.format("CALL izmenaAdrese('%s', '%s', '%s', '%s', '%s', '%s')", PK, pbr, grad, ulica, broj, PKlica));
			nasaKonekcija.close();
		} catch (SQLException joj)
		{
			joj.printStackTrace();
		}
	}
	
	public static void izmenaFirme(String PK, String JIB, String PIB,
			String naziv, String tel, String fax,
			String mail)
	{
		ozbiljnaKonekcija();
		try
		{
			kom.executeQuery(String.format("CALL izmenaFirme('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
					PK, JIB, PIB, naziv, tel, fax, mail));
			nasaKonekcija.close();
		} catch (SQLException joj)
		{
			joj.printStackTrace();
		}
	}

	public static void unosRacuna()
	{
		ozbiljnaKonekcija();
		try
		{
			kom.executeQuery(String.format("CALL unosRacuna()"));
			nasaKonekcija.close();
		} catch (SQLException joj)
		{
			joj.printStackTrace();
		}
	}

	public static void unosArtiklaSaRacuna(String IDarti, String kol)
	{
		ozbiljnaKonekcija();
		try
		{
			kom.executeQuery(String.format("CALL unosArtiklaSaRacuna('%s', '%s')", IDarti, kol));
			nasaKonekcija.close();
		} catch (SQLException joj)
		{
			joj.printStackTrace();
		}
	}

	public static void izmenaArtikla(String PK, String naziv, String lager, String ulazna,
			String marza, String porez)
	{
		ozbiljnaKonekcija();
		try
		{
			kom.executeQuery(String.format("CALL izmenaArtikla('%s', '%s', '%s', '%s', '%s', '%s')",
					PK, naziv, lager, ulazna, marza, porez));
			nasaKonekcija.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static int dajNaziveKolona(ResultSet rs) throws SQLException
	{
		int brojKolona = rs.getMetaData().getColumnCount();
		naziviKolona = new String[brojKolona - 1];
		for (int i = 2; i <= brojKolona; i++)
		{
			naziviKolona[i-2] = rs.getMetaData().getColumnName(i);
		}
		return brojKolona;
	}

}
