(ns tic-tac-toe.cli-main
  (:require [tic-tac-toe.game-setup :as game-setup]
            [tic-tac-toe.cli-runner :as cli-runner]))

(defn -main []
  (cli-runner/run (game-setup/new-game)))


