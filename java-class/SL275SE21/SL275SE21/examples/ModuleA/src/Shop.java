@BusinessPolicies({
        @BusinessPolicy(name="Returns Policy", countries="GB", value="4 weeks"),
        @BusinessPolicy(countries={"GB","FR"}, value="Ship via Dover-Calais")
})
public class Shop {
}
