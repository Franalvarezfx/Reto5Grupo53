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

public class Requerimiento_2Dao {
    
    public ArrayList<Requerimiento_2> requerimiento2() throws SQLException {
        

        ArrayList<Requerimiento_2> respuesta = new ArrayList<Requerimiento_2>();
        Connection conexion = JDBCUtilities.getConnection();
        String consulta = "select c.Proveedor, p.Constructora, c.Pagado from Compra c inner join Proyecto p ON c.ID_Proyecto = p.ID_Proyecto where (Proveedor = 'Homecenter' OR Proveedor ='JUMBO') and Pagado = 'Si' and Constructora like '%S.A.' order by  Proveedor ASC";
        try {
            // Recorrer los registros en los VO específicos
            Statement statement = conexion.createStatement();
            ResultSet resultados = statement.executeQuery(consulta);
            while (resultados.next()){
                Requerimiento_2 requerimiento_2 = new Requerimiento_2();
                requerimiento_2.setProveedor(resultados.getString("Proveedor"));
                requerimiento_2.setConstructora(resultados.getString("Constructora"));
                requerimiento_2.setPagado(resultados.getString("Pagado"));

                respuesta.add(requerimiento_2);
            }
            statement.close();
            resultados.close();

        } catch (SQLException e) {
            System.err.println("error al consultar el dato del segundo requerimiento");
                e.printStackTrace();

        } finally {
            if (conexion != null)
                conexion.close();

        }

        // Retornar la colección de vo's
        return respuesta;

    }
    
}
