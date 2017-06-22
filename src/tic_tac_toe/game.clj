(ns tic-tac-toe.game
  (:require [tic-tac-toe.helper :as helper]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.view :as view]
            [tic-tac-toe.move :as move]))

(defn get-marker [player-num]
  (let [marker (helper/prompt (str "Player " player-num "'s marker:"))]
    (if (not= (count marker) 1) (do (println "Marker can only be 1 character") (recur player-num)) marker)))


(defn game-turn [board current-marker prev-marker]
  (if (board/win? board)
    (do (println (view/str-format-board board)) (println (str prev-marker " WINS!")))
    (recur (board/apply-move board (move/get-move board) current-marker) prev-marker current-marker)))

(defn new-game [axis-size]
  (game-turn (board/generate-empty-board axis-size) (get-marker 1) (get-marker 2)))
