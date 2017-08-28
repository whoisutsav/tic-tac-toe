(ns tic-tac-toe.player-setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player-setup :refer :all]))


(describe "make-player"
          (around [it] (with-out-str (it)))

          (it "initializes human player"
              (should= {:type :human :marker :X} 
                       (with-in-str "X\nO" (initialize-player :human))))

          (it "initializes computer player"
              (should= {:type :computer :marker :X} 
                       (with-in-str "X\n" (initialize-player :computer :O)))))
