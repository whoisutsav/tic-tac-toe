(ns tic-tac-toe.core
  (:require  [tic-tac-toe.game :as game]))

(defn -main
  [& args]
  (game/new-game 3))
