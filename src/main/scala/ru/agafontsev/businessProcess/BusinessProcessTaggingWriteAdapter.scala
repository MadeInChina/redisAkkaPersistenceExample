package ru.agafontsev.businessProcess

import akka.persistence.journal.{Tagged, WriteEventAdapter}
import ru.agafontsev.businessProcess.BusinessProcessPersistent.InitBusinessProcess

class BusinessProcessTaggingWriteAdapter extends WriteEventAdapter {
  import BusinessProcessTaggingWriteAdapter._
  override def manifest(event: Any): String = ""

  override def toJournal(event: Any): Any = event match {
    case InitBusinessProcess(WorkflowId(wId)) => Tagged(event, Set(workflowTag(wId)))
  }
}

object BusinessProcessTaggingWriteAdapter {
  def workflowTag(workflowId: String): String = s"wf:$workflowId"
}
