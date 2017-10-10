(ns tic-tac-toe.core-console
  (:require [tic-tac-toe.core.board :as board]
            [tic-tac-toe.console.human]
            [tic-tac-toe.core.player.computer]
            [tic-tac-toe.core.player.hard-computer]
            [tic-tac-toe.console.game-setup :as console-game-setup]
            [tic-tac-toe.console.game-runner :as console-runner]))

(def configuration {
                    :board-size 3
                    :options [ "1. Human vs. Human" 
                               "2. Human vs. Computer"
                               "3. Human vs. Hard Computer" ]})

(defn -main []
  (-> (console-game-setup/setup-new configuration)
      (console-runner/run)))


