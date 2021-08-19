/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.JDBCUtilities;

public class Requerimiento_3Dao {
    public ArrayList<Requerimiento_3> requerimiento3() throws SQLException {
        
        ArrayList<Requerimiento_3> respuesta = new ArrayList<Requerimiento_3>();
        Connection conexion = JDBCUtilities.getConnection();
        String consulta = "SELECT Cargo, Salario FROM Lider GROUP BY Cargo HAVING MAX(Salario)>700000";
        try {
            // Recorrer los registros en los VO específicos
            Statement statement = conexion.createStatement();
            ResultSet resultados = statement.executeQuery(consulta);
            while (resultados.next()){
                Requerimiento_3 requerimiento_3 = new Requerimiento_3();
                requerimiento_3.setCargo(resultados.getString("Cargo"));
                requerimiento_3.setMaxSalario(resultados.getInt("Salario"));

                respuesta.add(requerimiento_3);
            }
            statement.close();
            resultados.close();

        } catch (SQLException e) {
            System.err.println("error al consultar el dato del tercer requerimiento");
                e.printStackTrace();

        } finally {
            if (conexion != null)
                conexion.close();

        }

        // Retornar la colección de vo's
        return respuesta;

    }
    
}
