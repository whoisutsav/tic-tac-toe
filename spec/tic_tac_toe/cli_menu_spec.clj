(ns tic-tac-toe.cli-menu-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.cli-menu :refer :all]))


(describe "get-options"
          (around [it] (with-out-str (it)))
          (it "returns human vs human players for first option"
              (should= [{:marker :X :type :human}
                        {:marker :O :type :human}]
                       (with-in-str "1\nX\nO\n" (get-options))))
          (it "returns human vs computer players for second option"
              (should= [{:marker :X :type :human}
                        {:marker :O :type :computer}]
                       (with-in-str "2\nX\nO\n" (get-options))))
          (it "prompts again upon invalid option"
              (should= [{:marker :X :type :human}
                        {:marker :O :type :computer}]
                       (with-in-str "3\n2\nX\nO\n" (get-options)))))
