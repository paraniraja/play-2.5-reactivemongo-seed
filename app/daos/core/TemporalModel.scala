package daos.core

import org.joda.time.DateTime

trait TemporalModel extends IdentifiableModel {

  var created: Option[DateTime]
  var updated: Option[DateTime]

}
