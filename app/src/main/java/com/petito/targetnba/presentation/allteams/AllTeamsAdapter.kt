package com.petito.targetnba.presentation.allteams

import android.view.LayoutInflater
import android.view.ViewGroup
import com.petito.targetnba.databinding.TeamItemBinding
import com.petito.targetnba.presentation.base.BaseRecyclerViewAdapter
import com.petito.targetnba.presentation.base.BaseViewHolder

class AllTeamsAdapter(items: MutableList<AllTeamsDataItem>, listener: AllTeamsAdapterListener) :
    BaseRecyclerViewAdapter<AllTeamsDataItem>(items, listener) {

    override fun getItemCount(): Int {
        return if (items.size > 0) items.size else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return AllTeamsViewHolder(
            TeamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    inner class AllTeamsViewHolder(private val mBinding: TeamItemBinding) :
        BaseViewHolder(mBinding.root) {
        override fun onBind(position: Int) {
            if (items.isNotEmpty()) {
                val teamDataItem = items[position]
                mBinding.allTeamsDataItem = teamDataItem
                mBinding.item = AllTeamsItemView { itemListener.onItemClick(teamDataItem) }
                mBinding.executePendingBindings()
            }
        }
    }
}