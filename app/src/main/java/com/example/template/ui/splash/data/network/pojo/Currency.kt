package com.example.template.ui.splash.data.network.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Currency(

    @SerializedName("Broj te\u010dajnice")
    @Expose
    var brojTeAjnice: String? = null,
    @SerializedName("Datum primjene")
    @Expose
    var datumPrimjene: String? = null,
    @SerializedName("Dr\u017eava")
    @Expose
    var drAva: String? = null,
    @SerializedName("\u0160ifra valute")
    @Expose
    var ifraValute: String? = null,
    @SerializedName("Valuta")
    @Expose
    var valuta: String? = null,
    @SerializedName("Jedinica")
    @Expose
    var jedinica: Int? = null,
    @SerializedName("Kupovni za devize")
    @Expose
    var kupovniZaDevize: String? = null,
    @SerializedName("Srednji za devize")
    @Expose
    var srednjiZaDevize: String? = null,
    @SerializedName("Prodajni za devize")
    @Expose
    var prodajniZaDevize: String? = null
)
