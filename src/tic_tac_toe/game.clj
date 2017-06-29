(ns tic-tac-toe.game
  (:require [tic-tac-toe.console-output :as console-output]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]))

(defn- switch-player [player]
  (case player
    :x :o
    :o :x))

(defn- take-turn [board markers player]
  (-> board
      (player/get-move (player markers) markers)
      (board/apply-move player)))

(defn- complete-game [markers board]
  (if-let [winner (decision/winner board)]
    (console-output/declare-winner (winner markers))
    (console-output/declare-draw)))

(defn game-loop [{:keys [markers board player]}]
  (if (decision/over? board)
      (complete-game markers board)
      (recur {
              :markers markers
              :board (take-turn board markers player)
              :player (switch-player player)})))


(defn new-game [board]
  (game-loop {
              :board board
              :markers {:x "X" :o "O"}
              :players [:x :o]}))
