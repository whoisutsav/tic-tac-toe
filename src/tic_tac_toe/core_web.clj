(ns tic-tac-toe.core-web
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [tic-tac-toe.player.human-web]
            [tic-tac-toe.player.computer]
            [tic-tac-toe.player.hard-computer]
            [tic-tac-toe.web.app :refer [app] ]))

(def port 3000)

(defn -main [] 
  (run-jetty app {:port port}))
