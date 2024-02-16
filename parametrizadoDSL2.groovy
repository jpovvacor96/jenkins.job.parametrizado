job('ejemplo3-job-DSL') {
	description('Job DSL de ejemplo para el curso de Jenkins. Vamos a probar ahora el push 3')
  	scm {
      		git('https://github.com/jpovvacor96/jenkins.job.parametrizado.git', 'main') { node ->
        		node / gitConfigName('jpovvacor96')
        		node / gitConfigEmail('jpovvacor96@gmail.com')
      		}
    	} 
  	parameters {
   		stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el Job Booleano')
      		choiceParam('planeta', ['Mercurio', 'Venus', 'Tierrra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
      		booleanParam('agente', false)
    	}
  	triggers {
    		cron('H/7 * * * *')
        	githubPush()
    	}
  	steps {
    		shell("bash jobscript.sh")
    	}
  	publishers {
      		mailer('jpovvacor96n@gmail.com', true, true)
      		slackNotifier {
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
