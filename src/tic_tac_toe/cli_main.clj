(ns tic-tac-toe.cli-main
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.cli-menu :as cli-menu]
            [tic-tac-toe.cli-runner :as cli-runner]))

(defn -main []
  (let [players (cli-menu/get-options)] 
    (cli-runner/run
      {:board (board/new-board)
       :current-player (first players) 
       :opponent-player (second players)})))


