(ns tic-tac-toe.player-setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player-setup :refer :all]))


(describe "initialize-players"
          (around [it] (with-out-str (it)))

          (it "initializes human-vs-human players"
              (should= [{:type :human :marker :X}
                        {:type :human :marker :O}]
                       (with-in-str "X\nO" (initialize-players :human-vs-human))))

          (it "initializes human-vs-computer players"
              (should= [{:type :human :marker :X}
                        {:type :computer :marker :O}]
                       (with-in-str "X\n" (initialize-players :human-vs-computer))))
          
          (it "initializes computer player with unique marker"
              (should= [{:type :human :marker :O}
                        {:type :computer :marker :X}]
                       (with-in-str "O\n" (initialize-players :human-vs-computer)))))
