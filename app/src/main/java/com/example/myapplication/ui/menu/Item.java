package com.example.myapplication.ui.menu;

class Item {
    private int familia, indisponivel, takeway, novidade, mostar, aipo, amendoins, crustaceos,
                diox, frutsec, gluten, leite, moluscos, mostarda, ovos, peixe, sesamo, tremocos;
    private String name, desc, classe;
    private double preco, precomeia;


    public Item(String name, double preco, int categoryID) {
        this.name = name;
        this.preco=preco;
        this.familia = categoryID;
    }


    public int getIndisponivel() {
        return indisponivel;
    }

    public void setIndisponivel(int indisponivel) {
        this.indisponivel = indisponivel;
    }

    public int getTakeway() {
        return takeway;
    }

    public void setTakeway(int takeway) {
        this.takeway = takeway;
    }

    public int getNovidade() {
        return novidade;
    }

    public void setNovidade(int novidade) {
        this.novidade = novidade;
    }

    public int getMostar() {
        return mostar;
    }

    public void setMostar(int mostar) {
        this.mostar = mostar;
    }

    public int getAipo() {
        return aipo;
    }

    public void setAipo(int aipo) {
        this.aipo = aipo;
    }

    public int getAmendoins() {
        return amendoins;
    }

    public void setAmendoins(int amendoins) {
        this.amendoins = amendoins;
    }

    public int getCrustaceos() {
        return crustaceos;
    }

    public void setCrustaceos(int crustaceos) {
        this.crustaceos = crustaceos;
    }

    public int getDiox() {
        return diox;
    }

    public void setDiox(int diox) {
        this.diox = diox;
    }

    public int getFrutsec() {
        return frutsec;
    }

    public void setFrutsec(int frutsec) {
        this.frutsec = frutsec;
    }

    public int getGluten() {
        return gluten;
    }

    public void setGluten(int gluten) {
        this.gluten = gluten;
    }

    public int getLeite() {
        return leite;
    }

    public void setLeite(int leite) {
        this.leite = leite;
    }

    public int getMoluscos() {
        return moluscos;
    }

    public void setMoluscos(int moluscos) {
        this.moluscos = moluscos;
    }

    public int getMostarda() {
        return mostarda;
    }

    public void setMostarda(int mostarda) {
        this.mostarda = mostarda;
    }

    public int getOvos() {
        return ovos;
    }

    public void setOvos(int ovos) {
        this.ovos = ovos;
    }

    public int getPeixe() {
        return peixe;
    }

    public void setPeixe(int peixe) {
        this.peixe = peixe;
    }

    public int getSesamo() {
        return sesamo;
    }

    public void setSesamo(int sesamo) {
        this.sesamo = sesamo;
    }

    public int getTremocos() {
        return tremocos;
    }

    public void setTremocos(int tremocos) {
        this.tremocos = tremocos;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPrecomeia() {
        return precomeia;
    }

    public void setPrecomeia(double precomeia) {
        this.precomeia = precomeia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFamilia() {
        return familia;
    }

    public void setFamilia(int familia) {
        this.familia = familia;
    }

    @Override
    public String toString() {
        return "Item{" +
                "familia=" + familia +
                ", indisponivel=" + indisponivel +
                ", takeway=" + takeway +
                ", novidade=" + novidade +
                ", mostar=" + mostar +
                ", aipo=" + aipo +
                ", amendoins=" + amendoins +
                ", crustaceos=" + crustaceos +
                ", diox=" + diox +
                ", frutsec=" + frutsec +
                ", gluten=" + gluten +
                ", leite=" + leite +
                ", moluscos=" + moluscos +
                ", mostarda=" + mostarda +
                ", ovos=" + ovos +
                ", peixe=" + peixe +
                ", sesamo=" + sesamo +
                ", tremocos=" + tremocos +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", classe='" + classe + '\'' +
                ", preco=" + preco +
                ", precomeia=" + precomeia +
                '}';
    }
}