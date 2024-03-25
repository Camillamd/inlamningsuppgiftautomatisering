Feature:Medlemsregistrering

  Scenario Outline: Felregistrering av lösenord
    Given Jag registrerar medlemmens födelsenummer <personnummer>
    And jag registrerar medlemmens namn <namn>
    And jag registrerar medlemmens efternamn <efternamn>
    And jag registrerar medlemmens e-mailadress
    And jag registrerar medlemmens e-mailadress igen
    And jag registrerar ett passord <losen>
    #And jag registrerar passordet igen <losenrepeat>
    When jag registrerar ett slumpat felaktigt passord
    And jag kryssar i andra boxen vid vilken roll
    #And jag kryssar i första rutan vid account confirmation
    #And jag kryssar i andra rutan vid account confirmation
    #And jag kryssar i första boxen vid communication preferences
    #And jag kryssar i andra rutan vid communication preferences
    #And jag kryssar i rutan vid code of ethics and conduct
    #When jag har registrerat namn och personnummer klickar jag på knappen Confirm and join
    #When registreringen utan efternamn är klar klickar jag på knappen join
    #Then är registreringen klar och jag kommer till min medlemssida
    Then får jag upp ett felmeddelande om fel lösenord

    Examples:

      | personnummer | namn     | efternamn | losen       |
      | 05/9/1998    | "Aurora" | "Green"   | "566kul988" |