job('ejemplo2-job-DSL'){
			description('Job DSL de ejemplo para el curso de Jenkins')
			scm{
				git('https://github.com/manuelVVrepositorioGIT/jenkins.job.parametrizado.git','main') { node ->
					node / gitConfigName('manuelVVrepositorioGIT')
					node / gitConfigEmail('manuelvvrepositoriogit@gmail.com')
				}
			}
  		parameters{
        stringParam('nombre', defaultValue='Manuel', description = 'Parametro de cadena para el job booleano')
				choiceParam('planeta',['Coruscant','Camino','Dagobah','Hooth', 'Alderan','Tatooine','Yavin'])
				booleanParam('agente',false)
      }
      triggers{
        cron('H/7 * * * *')
      }
      steps{
        shell("bash jobscript.sh")
      }
      publishers{
        mailer('manuel3791@gmail.com',true,true)
        slackNotifier{
          notifyAborted(true)
          notifyEveryFailure(true)
          notifyNotBuilt(false)
          notifyUnstable(false)
          notifyBackToNormal(true)
          notifySuccess(false)
          notifyRepeatedFailure(false)
          startNotification(false)
          includeTestSummary(false)
          includeCustomMessage(false)
          customMessage(null)
          sendAs(null)
          commitInfoChoice('NONE')
          teamDomain(null)
          authToken(null)
        }
      }
}
