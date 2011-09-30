/**
 * @author L2Rambase Project
 */
package l2.rambase.gameserver.model;

import javolution.util.FastList;


public class L2ExtractableSkill
	{
		private final int _hash;
		private final L2ExtractableProductItem[] _product;

		public L2ExtractableSkill(int hash, FastList<L2ExtractableProductItem> products)
		{
			_hash = hash;
			_product = new L2ExtractableProductItem[products.size()];
			products.toArray(_product);
		}

		public int getSkillHash()
		{
			return _hash;
		}

		public L2ExtractableProductItem[] getProductItemsArray()
		{
			return _product;
		}
	}