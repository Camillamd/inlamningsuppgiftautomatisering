Feature:Medlemsregistrering

  Scenario Outline: Godkänd Registrering
    Given Jag registrerar medlemmens födelsenummer <personnummer>
    And jag registrerar medlemmens namn <namn>
    And jag registrerar medlemmens efternamn <efternamn>
    And jag registrerar medlemmens e-mailadress
    And jag registrerar medlemmens e-mailadress igen
    And jag registrerar ett passord <losen>
    And jag registrerar passordet igen <losenrepeat>
    And jag kryssar i andra boxen vid vilken roll
    And jag kryssar i första rutan vid account confirmation
    And jag kryssar i andra rutan vid account confirmation
    And jag kryssar i första boxen vid communication preferences
    And jag kryssar i andra rutan vid communication preferences
    And jag kryssar i rutan vid code of ethics and conduct
    When jag har registrerat namn och personnummer klickar jag på knappen Confirm and join
    Then är registreringen klar och jag kommer till min medlemssida

    Examples:

      | personnummer | namn     | efternamn | losen       | losenrepeat |
      | 05/9/1998    | "Aurora" | "Green"  | "533uji988" | "533uji988" |