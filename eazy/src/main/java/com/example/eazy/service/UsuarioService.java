package com.example.eazy.service;

import com.example.eazy.dto.usuario.ConsumoMenorMaiorDTO;
import com.example.eazy.dto.usuario.UsuarioConsumoTotalDTO;
import io.swagger.v3.core.util.Json;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class UsuarioService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public UsuarioConsumoTotalDTO consumoEValorTotal(Long idUsuario) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_CONSULTAR_CONSUMO_E_VALOR_TOTAL_POR_USUARIO");
        query.registerStoredProcedureParameter("p_id_usuario", Long.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_consumo_total", Double.class, jakarta.persistence.ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_valor_total", Double.class, jakarta.persistence.ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_data_primeira_conta", Double.class, jakarta.persistence.ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_data_primeira_conta", Double.class, jakarta.persistence.ParameterMode.OUT);
        query.setParameter("p_id_usuario", idUsuario);

        query.execute();

        Double consumoTotal = (Double) query.getOutputParameterValue("p_consumo_total");
        Double valorTotal = (Double) query.getOutputParameterValue("p_valor_total");
        Date primeiraConta = (Date) query.getOutputParameterValue("p_data_primeira_conta");
        Date ultimaConta = (Date) query.getOutputParameterValue("p_data_primeira_conta");

        UsuarioConsumoTotalDTO usuarioConsumoTotalDTO = new UsuarioConsumoTotalDTO(consumoTotal, valorTotal, primeiraConta, ultimaConta);

        return usuarioConsumoTotalDTO;
    }

    @Transactional
    public ConsumoMenorMaiorDTO maiorConsumo(Long idUsuario) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_CONSULTAR_MENOR_GASTO_HISTORICO_POR_USUARIO");
        query.registerStoredProcedureParameter("p_id_usuario", Long.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_menor_gasto", Double.class, jakarta.persistence.ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_data_menor_gasto", Date.class, jakarta.persistence.ParameterMode.OUT);
        query.setParameter("p_id_usuario", idUsuario);

        query.execute();

        Double menorConsumo = (Double) query.getOutputParameterValue("p_menor_gasto");
        Date dataMenorConsumo = (Date) query.getOutputParameterValue("p_data_menor_gasto");

        ConsumoMenorMaiorDTO consumoMenorMaiorDTO = new ConsumoMenorMaiorDTO(menorConsumo, dataMenorConsumo);

        return consumoMenorMaiorDTO;
    }

    @Transactional
    public ConsumoMenorMaiorDTO menorConsumo(Long idUsuario) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_CONSULTAR_MENOR_GASTO_HISTORICO_POR_USUARIO");
        query.registerStoredProcedureParameter("p_id_usuario", Long.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("p_maior_gasto", Double.class, jakarta.persistence.ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_data_maior_gasto", Date.class, jakarta.persistence.ParameterMode.OUT);
        query.setParameter("p_id_usuario", idUsuario);

        query.execute();

        Double maiorConsumo = (Double) query.getOutputParameterValue("p_maior_gasto");
        Date dataMaiorConsumo = (Date) query.getOutputParameterValue("p_data_maior_gasto");

        ConsumoMenorMaiorDTO consumoMenorMaiorDTO = new ConsumoMenorMaiorDTO(maiorConsumo, dataMaiorConsumo);

        return consumoMenorMaiorDTO;
    }
}