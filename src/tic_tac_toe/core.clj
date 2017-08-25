(ns tic-tac-toe.core
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.console-game-setup :as console-game-setup]
            [tic-tac-toe.console-runner :as console-runner]))

(def configuration {
                    :board-size 4
                    :options [ "1. Human vs. Human" 
                               "2. Human vs. Computer"
                               "3. Human vs. Hard Computer" ]})

(defn -main []
  (-> (console-game-setup/new-game configuration)
      (console-runner/run)))


