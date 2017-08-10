(ns tic-tac-toe.console-main
  (:require [tic-tac-toe.console-game-setup :as console-game-setup]
            [tic-tac-toe.console-runner :as console-runner]))

(defn -main []
  (console-runner/run (console-game-setup/new-game)))


