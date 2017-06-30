(ns tic-tac-toe.cli-main
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.cli-runner :as cli-runner]))

(defn- get-player-markers []
  (let [marker1 (player/get-marker 1)
        marker2 (player/get-marker 2)]
    {:x marker1 :o marker2})) 

(defn -main []
  (-> {:board (board/new-board)}
      (into {:markers (get-player-markers)})
      (into {:player :x})
      (cli-runner/run)))


