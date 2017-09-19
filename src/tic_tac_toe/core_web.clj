(ns tic-tac-toe.core_web
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [tic-tac-toe.web.app :refer [app] ]))

(run-jetty app {:port 3000})
