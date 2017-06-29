(ns tic-tac-toe.cli-main
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.cli-runner :as cli-runner]))

(defn- get-markers []
  (console/show-message "Player 1 enter your marker")
  (let [markers (assoc {} :x (console/get-user-input))]
    (console/show-message "Player 2 enter your marker")
    (assoc markers :o (console/get-user-input)))) 

(defn -main []
  (-> {}
      (into {:board (board/new-board)})
      (into {:markers (get-markers)})
      (into {:player :x})
      (cli-runner/run)))


