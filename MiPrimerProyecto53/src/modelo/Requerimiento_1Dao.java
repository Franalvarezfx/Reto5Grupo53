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

public class Requerimiento_1Dao {
    public ArrayList<Requerimiento_1> requerimiento1() throws SQLException {
        
        ArrayList<Requerimiento_1> respuesta = new ArrayList<Requerimiento_1>();
        Connection conexion = JDBCUtilities.getConnection();

        try {

            String consulta = "select Constructora, Ciudad, Banco_Vinculado, Porcentaje_Cuota_Inicial, Clasificacion, Fecha_Inicio from Proyecto where Ciudad = 'Pereira'; ";

            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta);

            // Recorrer los registros en los VO específicos
            while (resultSet.next()) {

                Requerimiento_1 requerimiento1 = new Requerimiento_1();
                requerimiento1.setConstructora(resultSet.getString("Constructora"));
                requerimiento1.setCiudad(resultSet.getString("Ciudad"));
                requerimiento1.setBanco(resultSet.getString("Banco_Vinculado"));
                requerimiento1.setPorcentaje(resultSet.getDouble("Porcentaje_Cuota_Inicial"));
                requerimiento1.setClasificacion(resultSet.getString("Clasificacion"));
                requerimiento1.setFecha(resultSet.getString("Fecha_Inicio"));
                // Se agrega cada registro como un objeto del ArrayList que contiene la consulta
                respuesta.add(requerimiento1);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error consultando ranking de los 10 proyectos con mayor gasto por compras: " + e);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }

        // Retornar la colección de vo's
        return respuesta;

    }
    
}
