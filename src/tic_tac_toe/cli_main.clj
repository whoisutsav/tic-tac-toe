(ns tic-tac-toe.cli-main
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.cli-runner :as cli-runner]))

(defn -main []
  (let [current-marker (player/get-marker :primary) 
        opponent-marker (player/get-marker :opponent)] 
    (cli-runner/run
      {:board (board/new-board)
       :current-player {:marker current-marker :type :human}
       :opponent-player {:marker opponent-marker :type :computer}})))


