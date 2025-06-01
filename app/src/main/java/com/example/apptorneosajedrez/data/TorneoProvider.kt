package com.example.apptorneosajedrez.data

import com.example.apptorneosajedrez.model.TorneoModel
import kotlin.collections.listOf

object TorneosProvider {

    fun obtenerTorneos(): List<TorneoModel> {
        return torneosData
    }

    private val torneosData: List<TorneoModel> = listOf(
        TorneoModel(
            1,
            "Torneo Nacional",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aliquam erat volutpat."
        ),
        TorneoModel(
            2,
            "Copa Sur",
            "Cras lectus lacus, pharetra at leo ut, fringilla molestie tortor. Quisque ultrices, lectus non auctor imperdiet, neque odio ultrices diam, at egestas nulla diam tempus orci. Nunc vel tempor urna, eu consectetur nunc. Nunc tempus cursus ipsum, eget imperdiet ipsum pharetra a. Proin at arcu justo"
        ),
        TorneoModel(
            3,
            "Torneo Abierto",
            "Curabitur semper malesuada orci, id pharetra risus rhoncus et. Praesent vulputate neque eu nisi aliquet malesuada. Aliquam erat volutpat. Phasellus sed feugiat tellus, nec varius augue. Nulla viverra aliquet orci vel lobortis. Vestibulum luctus est sed est porttitor dictum."
        ),
        TorneoModel(
            4,
            "Torneo Abierto de Ajedrez Ciudad de Buenos Aires",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
        ),
        TorneoModel(
            5,
            "Memorial Miguel Najdorf",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
        ),
        TorneoModel(
            6,
            "Campeonato Argentino de Ajedrez",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
        ),
        TorneoModel(
            7,
            "Torneo Magistral de Mar del Plata",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
        ),
        TorneoModel(
            8,
            "Copa Independencia de Ajedrez",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
        ),
        TorneoModel(
            9,
            "Festival Internacional de Ajedrez de La Plata",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
        ),
        TorneoModel(
            10,
            "Torneo Abierto de Ajedrez Córdoba Capital",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
        ),
        TorneoModel(
            11,
            "Gran Prix de Ajedrez Patagonia",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
        ),
        TorneoModel(
            12,
            "Torneo de Ajedrez Mendoza",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
        ),
        TorneoModel(
            13,
            "Open de Ajedrez Rosario",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a hendrerit est, eu lobortis quam. Proin eu massa in enim lobortis vestibulum sit amet sed purus. Vestibulum consequat lorem ut suscipit rutrum. Duis lobortis sed justo ac dignissim. In luctus sed diam et vehicula. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."
        )
    )
}