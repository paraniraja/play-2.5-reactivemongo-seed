package com.scalableminds.mongev

import play.api.Configuration
import play.api.Environment
import play.api.inject.Binding
import play.api.inject.Module

class MongevModule extends Module {
  def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = {
    Seq(
      bind(classOf[MongevPlugin]).to(classOf[MongevPluginImpl]).eagerly()
    )
  }
}
