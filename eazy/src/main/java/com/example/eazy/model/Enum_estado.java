package com.example.eazy.model;

public enum Enum_estado
        {
            Acre("Acre"),
            Alagoas("Alagoas"),
            Amapá("Amapá"),
            Amazonas("Amazonas"),
            Bahia("Bahia"),
            Ceará("Ceará"),
            DistritoFederal("Distrito Federal"),
            EspíritoSanto("Espírito Santo"),
            Goiás("Goiás"),
            Maranhão("Maranhão"),
            MatoGrosso("Mato Grosso"),
            MatoGrossoDo1Sul("Mato Grosso do Sul"),
            MinasGerais("Minas Gerais"),
            Pará("Pará"),
            Paraíba("Paraíba"),
            Paraná("Paraná"),
            Pernambuco("Pernambuco"),
            Piauí("Piauí"),
            RioDeJaneiro("Rio de Janeiro"),
            RioGrandeDoNorte("Rio Grande do Norte"),
            RioGrandeDoSul("Rio Grande do Sul"),
            Rondônia("Rondônia"),
            Roraima("Roraima"),
            SantaCatarina("Santa Catarina"),
            SãoPaulo("São Paulo"),
            Sergipe("Sergipe"),
            Tocantins("Tocantins");

            private String estado;

            Enum_estado(String descricao) {
                this.estado = descricao;
            }

            @Override
            public String toString() {
                return estado;
            }
}
