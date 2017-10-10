(ns tic-tac-toe.console.game-runner
  (:require [tic-tac-toe.console.ui :as console-ui]
            [tic-tac-toe.core.basic-game :as basic-game]
            [tic-tac-toe.core.player :as player]
            [tic-tac-toe.core.board :as board]
            [tic-tac-toe.core.decision :as decision]))


(defn- take-turn [game]
  (console-ui/print-board (:board game))
  (console-ui/print-turn-prompt (-> game :current-player :marker))
  (basic-game/take-turn game))

(defn- end-game [board]
  (console-ui/print-board board)
  (if-let [winner (decision/winner board)]
    (console-ui/declare-winner winner)
    (console-ui/declare-draw)))

(defn run [game]
  (if (decision/over? (:board game))
    (end-game (:board game))
    (-> (take-turn game) (recur))))

