
package javafxapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafxapplication1.models.Materia;

/**
 *
 * @author adrian
 */
public class Conexion {
        private String cadconexion = "jdbc:postgresql://localhost:5432/constancia_alumnos";
	private String user = "admin_alumnos";
	private String pass = "admin123";
	private String driver = "org.postgresql.Driver";
	
	private Connection con;
	static Materia [] materia_ene_jun =new Materia[20];
        static Materia [] materia_ago_dic =new Materia[20];
        static int con_alumnos1=0;
        static int con_alumnos2=0;
        
	public Conexion() {
		con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(cadconexion, user, pass);
			System.out.println("ConexiÃ³n establecida");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al conectar");
		}	
	}	
	
        public void get_profesor_ene_jun(String rfc)
        {   
           
            int i=0;
            try
            {
             Statement stmt = null;
            con	 = getCon();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from alumnos_atendidos where rfc="+"'"+rfc+"'"+" and periodo='ENERO-JUNIO'" );
            while ( rs.next() ) {
            int id = rs.getInt("id");
            String  rfc_ = rs.getString("rfc");
            String  profesor = rs.getString("profesor");
            String  cve_materia = rs.getString("cve_materia");
            String  materia = rs.getString("materia");
            String  cve_carrera = rs.getString("cve_carrera");
            String  carrera = rs.getString("carrera");
            String  nivel = rs.getString("nivel");
            int     no_alumnos  = rs.getInt("no_alumnos");
            String  periodo = rs.getString("periodo");
            int     anio  = rs.getInt("anio");
            materia_ene_jun[i]=new Materia(id,rfc_,profesor,cve_materia,materia,cve_carrera,carrera,nivel,no_alumnos,periodo,anio);
                System.out.println("Materia agregada"+materia_ene_jun[i].getCarrera());
                i++;
                con_alumnos1=i;
         
            }
         rs.close();
         stmt.close();
            }catch(Exception e)
            {
               e.printStackTrace();
            }
        }
        
        public void get_profesor_ago_dic(String rfc)
        {   
            
             int i=0;
            try
            {
             Statement stmt = null;
            con	 = getCon();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from alumnos_atendidos where rfc="+"'"+rfc+"'"+" and periodo='AGOSTO-DICIEMBRE'" );
            while ( rs.next() ) {
            int id = rs.getInt("id");
            String  rfc_ = rs.getString("rfc");
            String  profesor = rs.getString("profesor");
            String  cve_materia = rs.getString("cve_materia");
            String  materia = rs.getString("materia");
            String  cve_carrera = rs.getString("cve_carrera");
            String  carrera = rs.getString("carrera");
            String  nivel = rs.getString("nivel");
            int     no_alumnos  = rs.getInt("no_alumnos");
            String  periodo = rs.getString("periodo");
            int     anio  = rs.getInt("anio");
            materia_ago_dic[i]=new Materia(id,rfc_,profesor,cve_materia,materia,cve_carrera,carrera,nivel,no_alumnos,periodo,anio);
            
                System.out.println("Materia agregada"+materia_ago_dic[i].getMateria());
                i++;
                con_alumnos2=i;
            }
         rs.close();
         stmt.close();
            }catch(Exception e)
            {
               e.printStackTrace();
            }
        }
        
	public Connection getCon() {
		return con;
	}
}
