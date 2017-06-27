(ns tic-tac-toe.core
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.game :as game]))


(defn -main
  [& args]
  (game/new-game (board/new-board)))
