package model;

public enum ItemSegurar {
    NENHUM,

    ORBE_DE_VIDA {
        @Override
        public double getModificadorDeDanoCausado() {
            return 1.3;
        }

        @Override
        public int calcularDanoRecuoAoAtacar(int hpMaximoPortador) {
            return (int) Math.round(hpMaximoPortador * 0.1);
        }
    },

    CAPACETE_DE_PEDRA {
        @Override
        public int calcularDanoRecuoContraAtaque(int hpMaximoAtacante) {
            return (int) Math.round(hpMaximoAtacante / 6.0);
        }
    },

    COLETE_DE_ATAQUE {
        @Override
        public double getModificadorDeDefesa() {
            return 1.50;
        }

        @Override
        public boolean checarMovimentoPermitido(boolean isAtaqueOfensivo) {
            return isAtaqueOfensivo;
        }
    };

    public double getModificadorDeDanoCausado() {
        return 1.0;
    }
    public int calcularDanoRecuoAoAtacar(int hpMaximoPortador) {
        return 0;
    }
    public int calcularDanoRecuoContraAtaque(int hpMaximoAtacante) {
        return 0;
    }
    public double getModificadorDeDefesa() {
        return 1.0;
    }
    public boolean checarMovimentoPermitido(boolean isAtaqueOfensivo) {
        return true;
    }
}
