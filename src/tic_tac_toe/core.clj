(ns tic-tac-toe.core
  (:require [tic-tac-toe.console-game-setup :as console-game-setup]
            [tic-tac-toe.console-runner :as console-runner]))

(defn -main []
  (-> (console-game-setup/new-game)
      (console-runner/run)))


