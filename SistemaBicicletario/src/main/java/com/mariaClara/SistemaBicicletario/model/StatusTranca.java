package com.mariaClara.SistemaBicicletario.model;

public enum StatusTranca {
    LIVRE,
    OCUPADA,
    NOVA,
    APOSENTADA,
    EM_REPARO,
    REPARO_SOLICITADO, // naõ tinha no swagger mas era necessario para tirar tranca para reparo
}
