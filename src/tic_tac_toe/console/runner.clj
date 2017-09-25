(ns tic-tac-toe.console.runner
  (:require [tic-tac-toe.console.ui :as console-ui]
            [tic-tac-toe.basic-game :as basic-game]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.player.human]
            [tic-tac-toe.player.computer]
            [tic-tac-toe.player.hard-computer]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]))


(defn- take-console-turn [{:keys [board current-player opponent-player] :as game}]
  (console-ui/print-board board)
  (console-ui/print-turn-prompt (:marker current-player))
  (basic-game/take-turn game))

(defn- console-end [game]
  (let [board (:board game)] 
    (console-ui/print-board board)
    (if-let [winner (decision/winner board)]
      (console-ui/declare-winner winner)
      (console-ui/declare-draw))))

(defn run [game]
  (if (decision/over? (:board game))
    (console-end game)
    (-> (take-console-turn game)
        (recur))))

