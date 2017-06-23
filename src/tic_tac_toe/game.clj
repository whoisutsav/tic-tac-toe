(ns tic-tac-toe.game
  (:require [tic-tac-toe.helper :as helper]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.view :as view]
            [tic-tac-toe.move :as move]))

;(defn alter-board [board]
;  (-> board
;       (move/get-move)
;       (board/apply-move)))
;
;(defn game-loop [turn]
;  (if-let [winner (decision/winner (:board turn))]
;      (println (str "Player " winner " wins."))
;      (if (decision/filled? board)
;        (println "Cats game.")
;        (recur (assoc {:board (alter-board board)})))))

(defn new-game [size]
;  (game-turn {
;              :board (board/generate-empty-board size)
;              :markers [ (get-marker 1) (get-marker 2)]
;              :current-player 1})
;  
  )
